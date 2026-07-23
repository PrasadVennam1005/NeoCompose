plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.binaryCompatibilityValidator)
    alias(libs.plugins.dokka)
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.androidx.baselineprofile) apply false
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    ignoreFailures.set(true)
}

configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
    ignoreFailures = true
}

configure<kotlinx.validation.ApiValidationExtension> {
    ignoredProjects.addAll(listOf("sample"))
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    if (name != "sample") {
        apply(plugin = "org.jetbrains.dokka")
    }

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(false)
        verbose.set(true)
        android.set(true)
        outputToConsole.set(true)
        ignoreFailures.set(true)
    }

    configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        buildUponDefaultConfig = true
        allRules = false
        ignoreFailures = true
        config.setFrom(files("${project.rootDir}/config/detekt/detekt.yml"))
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }

    afterEvaluate {
        plugins.withId("maven-publish") {
            val dokkaJavadocJar by tasks.registering(Jar::class) {
                from(tasks.named("dokkaHtml"))
                archiveClassifier.set("javadoc")
            }

            configure<PublishingExtension> {
                publications.withType<MavenPublication>().configureEach {
                    if (name == "release") {
                        artifact(dokkaJavadocJar)
                    }
                }
            }

            apply(plugin = "signing")
            configure<SigningExtension> {
                val secretKey = providers.environmentVariable("GPG_SIGNING_SECRET_KEY")
                val password = providers.environmentVariable("GPG_SIGNING_PASSWORD")
                if (secretKey.isPresent()) {
                    useInMemoryPgpKeys(secretKey.get(), password.getOrElse(""))
                    sign(extensions.getByType<PublishingExtension>().publications)
                }
            }
        }
    }
}
