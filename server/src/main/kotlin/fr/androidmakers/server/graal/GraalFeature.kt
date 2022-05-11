package fr.androidmakers.server.graal

import com.oracle.svm.core.annotate.AutomaticFeature
import io.github.classgraph.ClassGraph
import org.graalvm.nativeimage.hosted.Feature
import org.graalvm.nativeimage.hosted.RuntimeReflection

//
//@AutomaticFeature
//class GraalFeature: Feature {
//    override fun beforeAnalysis(access: Feature.BeforeAnalysisAccess?) {
//        ClassGraph()
//            //.verbose() // Log to stderr
//            .enableClassInfo()
//            .enableAnnotationInfo()
//            .acceptPackages("fr.androidmakers.server.model") // Scan com.xyz and subpackages (omit to scan all packages)
//            .scan().use { scanResult ->                // Start the scan
//                scanResult.allStandardClasses.forEach {
//                    if (it.hasAnnotation("kotlinx.serialization.Serializable")) {
//                        println("registering ${it.name}")
//                        RuntimeReflection.register(it.loadClass())
//                    }
//                }
//            }
//    }
//}