# The Architecture manifest

## Structure

In fact, the system deals with 3 different activities:

* assessments handling
* response evaluation
* students handling
* ACL handling

So the system consists of 2 different microservices:

* assessment microservice
* response evaluation microservice

The students microservice is implemented in a different project.

## Interface

The JSON-RPC has been chosen.

## Stack

### Docker 

as the containerization system.

### Docker compose

as the orchestration (at least in the beginning).

### Environment

Amazon.
