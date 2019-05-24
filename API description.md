Control of components with limited resource# API Description

This document describes the API for the system. The system is based on JSON-RPC v.2.

## The terminology

Multiple choice is a form of an objective assessment in which
respondents are asked to select the only correct answer out of the
choices from a list.

Let us consider the main terms used in multiple choice assessments
(MCA). The main terms are:

-   the assessment itself,
-   the stem,
-   the option,
-   the answer,
-   the distractor, 
-   the response.

The assessment includes a list of tasks. The number of the tasks in an
assessment may vary, but typically an assessment consists of about 10-15
tasks.

Each task incorporates a stem, in other words a text description of what
is the task for testee to solve. In most cases it is presented in a form
of a question, sometimes including some additional material like
graphics, illustrations, and so on.

The task also includes an answer (or a few answers) - the correct
elements of the list the testee is to mark as correct.

The other part of the task is a set of distractors, or list elements the
testee is not to mark as correct.

The answers and distractors are called options.

The options may also incorporate some additional
elements like figures, diagrams or even some programming code to run as
Coursera assessments do.

And the response is a list of elements the testee marked as correct.

## The API description

Here is the description of the JSON-API functions. The first level is the entity, the second - method.

* topic
    * add
    * update
    * delete
* stem
    * add
    * update
    * delete
* option
    * add
    * update
    * delete
* assessment_module
    * add
    * deprecate
    * delete
* assessment
    * build
    * evaluate_answers
