package com.griffithindustries.samples.designer

import com.griffithindustries.samples.common.*
import com.inductiveautomation.ignition.client.icons.*
import com.inductiveautomation.ignition.common.BundleUtil
import com.inductiveautomation.ignition.common.licensing.*
import com.inductiveautomation.ignition.common.project.resource.*
import com.inductiveautomation.ignition.designer.model.*
import javax.swing.*

@Suppress("unused")
class MarkdownDesignerHook : AbstractDesignerModuleHook() {
    private lateinit var context: DesignerContext

    override fun startup(context: DesignerContext, activationState: LicenseState) {
        this.context = context

        context.registerResourceWorkspace(MarkdownWorkspace(context))

        BundleUtil.get().addBundle("markdown", this::class.java, "localization")
    }

    override fun shutdown() = Unit

    override fun getResourceCategoryKey(id: ProjectResourceId): String = when(id.resourceType) {
        MarkdownResource.RESOURCE_TYPE -> "markdown.resource.nouns"
        else -> super.getResourceCategoryKey(id)
    }

    override fun getResourceIcon(id: ProjectResourceId): Icon = when(id.resourceType) {
        MarkdownResource.RESOURCE_TYPE -> VectorIcons.get("resource-note")
        else -> super.getResourceIcon(id)
    }
}
