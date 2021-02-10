package com.griffithindustries.samples.common

import com.inductiveautomation.ignition.common.project.resource.*
import java.io.Serializable

data class MarkdownResource(val field: Int, val field2: Int) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1

        val RESOURCE_TYPE = ResourceType(MODULE_ID, TYPE_ID)
    }
}
