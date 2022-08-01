package com.griffithindustries.samples.designer

import com.griffithindustries.samples.common.*
import com.inductiveautomation.ignition.common.project.resource.*
import com.inductiveautomation.ignition.designer.tabbedworkspace.*
import net.miginfocom.swing.*
import org.fife.ui.rsyntaxtextarea.*
import org.fife.ui.rtextarea.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener
import javax.swing.text.Document

class MarkdownEditor(workspace: TabbedResourceWorkspace, path: ResourcePath) : ResourceEditor<MarkdownResource>(workspace, path) {
    private lateinit var textArea: RSyntaxTextArea

    override fun init(resource: MarkdownResource) {
        removeAll()
        layout = MigLayout("ins 0, fill")

        textArea = RSyntaxTextArea(resource.note).also {
            it.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_NONE
            add(RTextScrollPane(it), "grow")
            it.document.addDocumentListener { event: DocumentEvent -> commit() }
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

private fun Document.addDocumentListener(function: (DocumentEvent) -> Unit) {
    this.addDocumentListener(object : DocumentListener {
        override fun insertUpdate(e: DocumentEvent) {
            function(e)
        }

        override fun removeUpdate(e: DocumentEvent) {
            function(e)
        }

        override fun changedUpdate(e: DocumentEvent) {
            function(e)
        }

    })
}
