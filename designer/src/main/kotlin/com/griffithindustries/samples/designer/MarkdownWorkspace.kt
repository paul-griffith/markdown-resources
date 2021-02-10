package com.griffithindustries.samples.designer

import com.griffithindustries.samples.common.*
import com.inductiveautomation.ignition.client.icons.*
import com.inductiveautomation.ignition.common.project.resource.*
import com.inductiveautomation.ignition.common.util.*
import com.inductiveautomation.ignition.common.xmlserialization.serialization.*
import com.inductiveautomation.ignition.designer.model.*
import com.inductiveautomation.ignition.designer.navtree.model.*
import com.inductiveautomation.ignition.designer.tabbedworkspace.*
import com.inductiveautomation.ignition.designer.workspacewelcome.*
import java.lang.IllegalStateException
import java.util.*
import javax.swing.*

class MarkdownWorkspace(context: DesignerContext) : TabbedResourceWorkspace(
    context,
    ResourceDescriptor.builder()
        .resourceType(MarkdownResource.RESOURCE_TYPE)
        .rootFolderText("Markdown Notes")
        .rootIcon(VectorIcons.get("resource-note"))
        .navTreeLocation(999)
        .build()
) {

    override fun getKey() = "markdownEditor"

    override fun newResourceEditor(resourcePath: ResourcePath): ResourceEditor<MarkdownResource> {
        return MarkdownEditor(this, resourcePath)
    }

    override fun addNewResourceActions(folderNode: ResourceFolderNode, menu: JPopupMenu) {
        menu.add(object : NewResourceAction(this, folderNode) {
            override fun newResourceName() = "note"

            override fun createPrototype(): MarkdownResource {
                return MarkdownResource(123, 456)
            }

            init {
                putValue(Action.NAME, "New Resource")
                putValue(Action.SMALL_ICON, VectorIcons.get("resource-note"))
            }
        })
    }

    override fun createWorkspaceHomeTab(): Optional<JComponent> {
        return object : WorkspaceWelcomePanel("Markdown Notes Workspace Title", null, null) {
            override fun createPanels(): List<JComponent> {
                return listOf(
                    ResourceBuilderPanel(
                        context,
                        "markdown note",
                        MarkdownResource.RESOURCE_TYPE.rootPath(),
                        listOf(
                            ResourceBuilderDelegate.build(
                                "markdown note",
                                VectorIcons.get("resource-note")
                            ) { builder ->
                                val serializer = context.createSerializer()
                                val prototype = MarkdownResource(123, 456)
                                serializer.addObject(prototype)
                                val bytes = serializer.serializeBinary(true)
                                builder.putData(bytes)
                            }
                        ),
                        this@MarkdownWorkspace::open
                    ),
                    RecentlyModifiedTablePanel(
                        context,
                        MarkdownResource.RESOURCE_TYPE,
                        "markdown notes",
                        this@MarkdownWorkspace::open
                    )
                )
            }
        }.toOptional()
    }
}
