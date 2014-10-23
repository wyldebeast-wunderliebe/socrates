Socrates
========

Socrates is a Java implementation of a survey engine, that enables you
to publish surveys and collect data. This package specifies core
ideas, and provides a base implementation of those ideas so as to be
able to actually do something useful. You'll need a bit more than this
though, but other packages should be available for instance for
rendering, for defining surveys, etc.

Socrates is mainly based on XForms, so a full survey consists of four
parts:

1. data
2. model
3. layout
4. submission


Data
----

The data part defines the variables (or: fields) that will hold the
collected data. Fields may hold default values.


Model
-----

The model defines properties of the data, like whether a variable must
have a value for the data to be valid, or specifying the type of the
variable. The following properties may be set:

* relevant
* required
* readonly
* calculate
* constraint
* datatype

All properties except for the datatype can be specified in terms of an
(XPath) expression, like:

    relevant="true()"
    required="foo == 1"

To apply properties to variables, 'binding' is used. This is done
through expressions that specify to what variables the properties
bind.


Layout
------

The view part of the survey specifies what it 'looks like', although
in theory the survey could also be converted to automated
speech...

The layout specifies what kind of control is used to obtain data, so
for instance a _select_ form element to present a list of values to
the user for the question "What is your favourite color?".  A control
is bound to the variable using the same _bind_ attribute as for
properties.


Submission
----------

Specifies the way the actual data should be handled.
