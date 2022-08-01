plugins {
    kotlin("jvm")
    id("java-library")
}

dependencies {
    compileOnly(project(":common"))

    val sdkVersion: String by project.parent!!.extra
    compileOnly("com.inductiveautomation.ignitionsdk", "designer-api", sdkVersion)
    compileOnly("com.inductiveautomation.ignitionsdk", "ignition-common", sdkVersion)

    modlApi(kotlin("stdlib"))
}
