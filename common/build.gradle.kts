plugins {
    kotlin("jvm")
    id("java-library")
}

dependencies {
    val sdkVersion: String by project.parent!!.extra
    compileOnly("com.inductiveautomation.ignitionsdk", "ignition-common", sdkVersion)
}
