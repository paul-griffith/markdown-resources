package com.griffithindustries.samples.gateway

import com.inductiveautomation.ignition.common.licensing.*
import com.inductiveautomation.ignition.common.script.hints.*
import com.inductiveautomation.ignition.gateway.model.*

class MarkdownGatewayHook : AbstractGatewayModuleHook() {
    lateinit var context: GatewayContext
    /**
     * Called to before startup. This is the chance for the module to add its extension points and update persistent
     * records and schemas. None of the managers will be started up at this point, but the extension point managers will
     * accept extension point types.
     */
    override fun setup(context: GatewayContext) {
        this.context = context
    }

    override fun startup(activationState: LicenseState) {}

    override fun shutdown() {}

    override fun isFreeModule(): Boolean = true

    override fun isMakerEditionCompatible() = true

}
