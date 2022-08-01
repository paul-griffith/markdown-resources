import org.jetbrains.kotlin.gradle.tasks.*

plugins {
    kotlin("jvm") version "1.4.30"
    id("io.ia.sdk.modl") version ("0.1.0")
}

version = "0.0.1-SNAPSHOT"

extra["sdkVersion"] = "8.1.0-SNAPSHOT"

ignitionModule {
    name.set("Markdown Resources")
    fileName.set("Markdown-Resources.modl")
    id.set("com.griffithindustries.samples.MarkdownResources")
    moduleVersion.set("$version")
    moduleDescription.set("A proof-of-concept implementation of a project resource + editor in Ignition 8.1.X's project resource system")
    requiredIgnitionVersion.set("8.1.0")
    projectScopes.set(mapOf(
        ":common" to "GD",
        ":designer" to "D",
        ":gateway" to "G"
    ))
    moduleDependencies.set(emptyMap())
    hooks.set(mapOf(
        "com.griffithindustries.samples.gateway.MarkdownGatewayHook" to "G",
        "com.griffithindustries.samples.designer.MarkdownDesignerHook" to "D"
    ))
}

tasks.getByName("signModule").enabled = false

allprojects {
    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "11"
                freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
            }
        }
    }
}
