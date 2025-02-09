The library supports the following features:

## General

* Usage of Open Feign library to allow for high-customizable REST client
* Provides a SpringBoot starter for usage in standalone client mode
* Provides a SpringBoot starter for usage inside a process application
* Decode HTTP error response and simulate exceptions, as if they are thrown locally by the service.
* Implemented Services: `RuntimeService`, `RepositoryService`, `TaskService`, `ExternalTaskService`

## RuntimeService

*  Process start by key: `#startProcessInstanceByKey()`
*  Process start by id: `#startProcessInstanceById()`
*  Process instance query: `#createProcessInstanceQuery()`
*  Message correlation: `#correlateMessage()`, `#createMessageCorrelation()`
*  Signal event: `#signalEventReceived()`, `#createSignalEvent()`
*  Execution trigger: `#signal()`
*  Read variables: `#getVariable()`,`#getVariables()`, `#getVariableTyped()`, `#getVariablesTyped()`
*  Read local variables: `#getVariableLocal()`,`#getVariablesLocal()`, `#getVariableLocalTyped()`, `#getVariablesLocalTyped()`
*  Write variables: `#setVariable()`,`#setVariables()`, `#setVariableTyped()`, `#setVariablesTyped()`
*  Delete variables: `#removeVariable()`,`#removeVariables()`
*  Write local variables: `#setVariableLocal()`,`#setVariablesLocal()`, `#setVariableLocalTyped()`, `#setVariablesLocalTyped()`
*  Delete local variables: `#removeVariableLocal()`,`#removeVariablesLocal()`

## RepositoryService

* Query for process definitions: `#createProcessDefinitionQuery()`

## TaskService 

* Query for tasks: `#createTaskQuery()`
* Task assignment: `#claim()`, `#defer()`, `#resolve()`, `#addCandidateGroup()`, `#deleteCandidateGroup()`, `#addCadidateUser()`, `#deleteCandidateUser()`
* Identity links: `#addUserIdentityLink()`, `#addGroupIdentityLink()`, `#deleteUserIdentityLink()`, `#deleteGroupIdentityLink()`,
* Task completion: `#complete()`
* Task deletion: `#deleteTasks()`
* Task attributes: `#setPriority()`, `#setOwner()`, `#setAssignee()`, `#saveTask()`
* Handling Errors and Escalation: `#handleBpmnError()`, `#handleEscalation()`
* Read variables: `#getVariable()`,`#getVariables()`, `#getVariableTyped()`, `#getVariablesTyped()`
* Read local variables: `#getVariableLocal()`,`#getVariablesLocal()`, `#getVariableLocalTyped()`, `#getVariablesLocalTyped()`
* Write variables: `#setVariable()`,`#setVariables()`, `#setVariableTyped()`, `#setVariablesTyped()`
* Delete variables: `#removeVariable()`,`#removeVariables()`
* Write local variables: `#setVariableLocal()`,`#setVariablesLocal()`, `#setVariableLocalTyped()`, `#setVariablesLocalTyped()`
* Delete local variables: `#removeVariableLocal()`,`#removeVariablesLocal()`

## ExternalTaskService

We are not aiming to replace the existing [External Task Client](https://docs.camunda.org/manual/latest/user-guide/ext-client/),
but still provide an alternative implementation for some methods.

* Complete a task by id: `#complete()`
* Handle BPMN Errors: `#handleBpmnError()`
* Handle failures: `#handleFailure()`
