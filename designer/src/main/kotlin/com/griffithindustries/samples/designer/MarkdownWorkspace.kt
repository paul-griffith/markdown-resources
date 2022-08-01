package com.griffithindustries.samples.designer

import com.griffithindustries.samples.common.*
import com.inductiveautomation.ignition.client.icons.*
import com.inductiveautomation.ignition.common.project.resource.*
import com.inductiveautomation.ignition.designer.model.*
import com.inductiveautomation.ignition.designer.tabbedworkspace.*
import com.inductiveautomation.ignition.designer.workspacewelcome.*
import java.util.*
import javax.swing.*

class MarkdownWorkspace(context: DesignerContext) : TabbedResourceWorkspace(context,
    ResourceDescriptor.builder()
        .resourceType(MarkdownResource.RESOURCE_TYPE)
        .nounKey("markdown.resource.noun")
        .rootFolderText("Markdown Notes")
        .rootIcon(VectorIcons.get("resource-note"))
        .navTreeLocation(999)
        .build()) {

    override fun getKey() = "markdownEditor"

    override fun newResourceEditor(resourcePath: ResourcePath): ResourceEditor<MarkdownResource> {
        return MarkdownEditor(this, resourcePath)
    }

    private val newMarkdownNote: (ProjectResourceBuilder) -> Unit = { builder ->
        builder.putData(MarkdownResource.DATA_KEY, "Enter a note".toByteArray())
    }

    override fun addNewResourceActions(folderNode: ResourceFolderNode, menu: JPopupMenu) {
        menu.add(object : NewResourceAction(this, folderNode, newMarkdownNote) {
            override fun newResourceName() = "note"

            init {
                putValue(Action.NAME, "New Note")
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
                                VectorIcons.get("resource-note"),
                                newMarkdownNote
                            )
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
