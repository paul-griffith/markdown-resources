package com.griffithindustries.samples.designer

import com.griffithindustries.samples.common.MarkdownResource
import com.inductiveautomation.ignition.client.util.gui.CatchAllListener
import com.inductiveautomation.ignition.common.project.resource.ProjectResource
import com.inductiveautomation.ignition.common.project.resource.ProjectResourceBuilder
import com.inductiveautomation.ignition.common.project.resource.ResourcePath
import com.inductiveautomation.ignition.designer.tabbedworkspace.ResourceEditor
import com.inductiveautomation.ignition.designer.tabbedworkspace.TabbedResourceWorkspace
import net.miginfocom.swing.MigLayout
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea
import org.fife.ui.rsyntaxtextarea.SyntaxConstants
import org.fife.ui.rtextarea.RTextScrollPane

class MarkdownEditor(
    workspace: TabbedResourceWorkspace,
    path: ResourcePath
) : ResourceEditor<MarkdownResource>(workspace, path) {
    private lateinit var textArea: RSyntaxTextArea

    override fun init(resource: MarkdownResource) {
        removeAll()
        layout = MigLayout("ins 0, fill")

        textArea = RSyntaxTextArea(resource.note).apply {
            syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_NONE
            add(RTextScrollPane(this), "grow")
            document.addDocumentListener(object : CatchAllListener() {
                override fun onChange() = commit()
            })
        }
    }

    override fun getObjectForSave(): MarkdownResource {
        return MarkdownResource(textArea.text)
    }

    override fun deserialize(resource: ProjectResource): MarkdownResource {
        return resource.getData(MarkdownResource.DATA_KEY)
            ?.takeIf(ByteArray::isNotEmpty)
            ?.toString(Charsets.UTF_8)
            .let { MarkdownResource(it.orEmpty()) }
    }

    override fun serializeResource(builder: ProjectResourceBuilder, resource: MarkdownResource) {
        builder.putData(MarkdownResource.DATA_KEY, resource.note.toByteArray())
    }
}
