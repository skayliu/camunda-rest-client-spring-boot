package org.camunda.bpm.extension.rest.impl.builder

import org.camunda.bpm.engine.repository.UpdateProcessDefinitionSuspensionStateBuilder
import org.camunda.bpm.engine.repository.UpdateProcessDefinitionSuspensionStateSelectBuilder
import org.camunda.bpm.engine.repository.UpdateProcessDefinitionSuspensionStateTenantBuilder
import org.camunda.bpm.extension.rest.client.api.ProcessDefinitionApiClient
import org.camunda.bpm.extension.rest.client.model.ProcessDefinitionSuspensionStateDto
import java.util.*

class RemoteUpdateProcessDefinitionSuspensionStateSelectBuilder(
  private val processDefinitionApiClient: ProcessDefinitionApiClient
) : UpdateProcessDefinitionSuspensionStateSelectBuilder {

  override fun byProcessDefinitionId(processDefinitionId: String?) =
    RemoteUpdateProcessDefinitionSuspensionStateBuilder(processDefinitionApiClient, processDefinitionId)

  override fun byProcessDefinitionKey(processDefinitionKey: String?) =
    RemoteUpdateProcessDefinitionSuspensionStateTenantBuilder(processDefinitionApiClient, processDefinitionKey)

}

abstract class BaseRemoteUpdateProcessDefinitionSuspensionStateBuilder : UpdateProcessDefinitionSuspensionStateBuilder {

  protected var includeProcessInstances: Boolean? = null
  protected var executionDate: Date? = null

  override fun includeProcessInstances(includeProcessInstances: Boolean): UpdateProcessDefinitionSuspensionStateBuilder = this.apply {
    this.includeProcessInstances = includeProcessInstances
  }

  override fun executionDate(executionDate: Date?): UpdateProcessDefinitionSuspensionStateBuilder = this.apply {
    this.executionDate = executionDate
  }

  override fun activate() = doUpdate(true)

  override fun suspend() = doUpdate(false)

  protected abstract fun doUpdate(active: Boolean)

}

class RemoteUpdateProcessDefinitionSuspensionStateBuilder(
  private val processDefinitionApiClient: ProcessDefinitionApiClient,
  private val processDefinitionId: String?
) : BaseRemoteUpdateProcessDefinitionSuspensionStateBuilder() {

  override fun doUpdate(active: Boolean) {
    processDefinitionApiClient.updateProcessDefinitionSuspensionStateById(processDefinitionId,
      ProcessDefinitionSuspensionStateDto()
        .includeProcessInstances(includeProcessInstances)
        .executionDate(executionDate)
        .suspended(!active)
    )
  }

}

class RemoteUpdateProcessDefinitionSuspensionStateTenantBuilder(
  private val processDefinitionApiClient: ProcessDefinitionApiClient,
  private val processDefinitionKey: String?
) : BaseRemoteUpdateProcessDefinitionSuspensionStateBuilder(), UpdateProcessDefinitionSuspensionStateTenantBuilder {

  private var withoutTenant: Boolean? = null
  private var tenantId: String? = null

  override fun processDefinitionWithoutTenantId() = this.apply {
    this.withoutTenant = true
  }

  override fun processDefinitionTenantId(tenantId: String?) = this.apply {
    this.tenantId = tenantId
  }

  override fun doUpdate(active: Boolean) {
    if (withoutTenant == true || tenantId == null) {
      processDefinitionApiClient.updateProcessDefinitionSuspensionStateByKey(processDefinitionKey,
        ProcessDefinitionSuspensionStateDto()
          .includeProcessInstances(includeProcessInstances)
          .executionDate(executionDate)
          .suspended(!active)
      )
    } else {
      processDefinitionApiClient.updateProcessDefinitionSuspensionStateByKeyAndTenantId(processDefinitionKey, tenantId,
        ProcessDefinitionSuspensionStateDto()
          .includeProcessInstances(includeProcessInstances)
          .executionDate(executionDate)
          .suspended(!active)
      )
    }
  }

}
