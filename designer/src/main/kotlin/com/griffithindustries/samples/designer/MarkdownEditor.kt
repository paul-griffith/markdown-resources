package com.griffithindustries.samples.designer

import com.griffithindustries.samples.common.*
import com.inductiveautomation.ignition.common.project.resource.*
import com.inductiveautomation.ignition.designer.tabbedworkspace.*
import net.miginfocom.swing.*
import org.fife.ui.rsyntaxtextarea.*
import org.fife.ui.rtextarea.*
import javax.swing.*

class MarkdownEditor(workspace: TabbedResourceWorkspace, path: ResourcePath) : ResourceEditor<MarkdownResource>(workspace, path) {
    private lateinit var field1: JSpinner
    private lateinit var field2: JSpinner

    override fun init(resource: MarkdownResource) {
        removeAll()
        layout = MigLayout("ins 0, fill")

        field1 = JSpinner(SpinnerNumberModel(resource.field, 0, 100, 1)).also {
            add(it, "wrap")
        }
        field2 = JSpinner(SpinnerNumberModel(resource.field2, 0, 100, 1)).also {
            add(it, "wrap")
        }
    }

    override fun getObjectForSave(): MarkdownResource {
        return MarkdownResource(field1.value as Int, field2.value as Int)
    }
}
