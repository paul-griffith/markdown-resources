package com.griffithindustries.samples.common

import com.inductiveautomation.ignition.common.project.resource.*

data class MarkdownResource(val note: String) {
    companion object {
        val RESOURCE_TYPE = ResourceType(MODULE_ID, TYPE_ID)
        const val DATA_KEY = "note.md"
    }
}
