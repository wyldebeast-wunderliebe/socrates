/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * You should have received a copy of the GNU General Public License
 * (for example /usr/src/linux/COPYING); if not, write to the Free
 * Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package com.w20e.socrates.process;

import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration.Configuration;

import com.w20e.socrates.data.Instance;
import com.w20e.socrates.data.Node;
import com.w20e.socrates.factories.QuestionnaireFactory;
import com.w20e.socrates.model.InstanceImpl;
import com.w20e.socrates.model.Model;
import com.w20e.socrates.model.NodeImpl;
import com.w20e.socrates.model.Questionnaire;
import com.w20e.socrates.model.Submission;
import com.w20e.socrates.model.SubmissionImpl;
import com.w20e.socrates.rendering.RenderConfig;

/**
 * @author dokter Holds all models, and creates new ones if necessary. The
 *         resource uses the model's factory property to create models,
 *         instances and render config objects.
 */
public final class ModelResource {

    /**
     * Initialize this class' logging.
     */
    private static final Logger LOGGER = Logger.getLogger(ModelResource.class
            .getName());

    /**
     * Hold singleton instance.
     */
    private static ModelResource instance = null;

    /**
     * Cache checking thread.
     */
    private ModelCacheThread cacheThread;

    /**
     * Hold all models.
     */
    private Map<URI, Questionnaire> models = new HashMap<URI, Questionnaire>();

    /**
     * Hold factories for creating new models.
     */
    private Map<String, QuestionnaireFactory> factories = new ConcurrentHashMap<String, QuestionnaireFactory>();

    /**
     * Hide constructor. Start model up-to-date checks.
     * 
     * @todo Determine what checks are actually needed by configuration.
     */
    private ModelResource() {

        this.cacheThread = new ModelCacheThread();
        this.cacheThread.register("file", new FileModelUpToDateCheck());
        this.cacheThread.start();
    }

    /**
     * Return the singleton instance for this resource.
     * 
     * @return the instance.
     * @todo make registry for uptodate handlers configurable
     */
    public static synchronized ModelResource getInstance() {

        if (ModelResource.instance == null) {
            ModelResource.instance = new ModelResource();
        }

        return ModelResource.instance;
    }

    /**
     * Get the model identified by id, and create new if necessary.
     * 
     * @param id
     *            the model's id.
     * @param cfg
     *            Configuration resource.
     * @return the model found, or null.
     * @throws Exception
     *             in case of any failure.
     */
    public Model getModel(final URI id, final Configuration cfg)
            throws Exception {

        LOGGER.fine("Fetching model " + id);

        Model m = getModelDefinition(id, cfg).getModel(id.toString());

        if (m == null) {
            return m;
        }

        Submission submission = new SubmissionImpl();
        String action = cfg.getString("submission.type", "file") + ":"
                + cfg.getString("submission.basedir", ".");
        submission.setAction(new URI(action));
        m.setSubmission(submission);

        return m;
    }

    /**
     * Create a new instance for this id. This will always be a statefull.
     * Instance, hence the 'create' name, instead of the usual 'get'.
     * 
     * @param id
     *            model id
     * @param cfg
     *            Configuration resource
     * @return new instance
     * @throws Exception
     *             in case the model can't be created.
     */
    public Instance createInstance(final URI id, final Configuration cfg)
            throws Exception {

        InstanceImpl inst = new InstanceImpl();
        Node node;

        for (Iterator<Node> i = getModelDefinition(id, cfg).getInstance("")
                .getAllNodes().iterator(); i.hasNext();) {
            node = i.next();
            inst.addNode(new NodeImpl(node.getName(), node.getValue()));
        }

        return inst;
    }

    /**
     * Return the rendering info for the given model.
     * 
     * @param id
     *            the model's id
     * @param cfg
     *            Confirutation resource
     * @return the rendering configuration
     * @throws Exception
     *             in case the rendering couldn't be parsed
     */
    public synchronized RenderConfig getRenderConfig(final URI id,
            final Configuration cfg) throws Exception {

        return getModelDefinition(id, cfg).getRenderConfig();
    }

    /**
     * Get the set of all model id's.
     * 
     * @return a set of all models.
     */
    public Set<URI> getModelIds() {

        return this.models.keySet();
    }

    /**
     * Remove the model (and rendering) for id.
     * 
     * @param id
     *            the model to remove.
     */
    public synchronized void removeModel(final URI id) {

        LOGGER.fine("Removing model " + id);

        if (this.models.remove(id) == null) {
            LOGGER.log(Level.WARNING, "Couldn't remove model");
        }
    }

    /**
     * Get the model definition. Create if necessary.
     * 
     * @param id
     *            Model id
     * @param cfg
     *            Congfiguration
     * @return the model definition.
     * @throws Exception
     *             in case the definition can't be created.
     */
    private synchronized Questionnaire getModelDefinition(final URI id,
            final Configuration cfg) throws Exception {

        if (!this.models.containsKey(id)) {

            this.models.put(id, getFactory(cfg.getString("model.factory"))
                    .createQuestionnaire(id));
        }

        return this.models.get(id);
    }

    /**
     * Create a new factory for Models.
     * 
     * @param className
     *            Create factory for the given class name
     * @return the factory found, or null if none found.
     * @throws Exception
     *             in case the factory can't created
     */
    private QuestionnaireFactory getFactory(final String className)
            throws Exception {

        if (!this.factories.containsKey(className)) {
            this.factories.put(className, (QuestionnaireFactory) Class.forName(
                    className).newInstance());
        }

        return this.factories.get(className);
    }
}
