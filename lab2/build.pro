import static com.github.forax.pro.Pro.*;
import static com.github.forax.pro.builder.Builders.*;

resolver.
    checkForUpdate(true).
    dependencies(
        // JUnit 5
        "org.junit.jupiter.api=org.junit.jupiter:junit-jupiter-api:5.2.0",
        "org.junit.platform.commons=org.junit.platform:junit-platform-commons:1.2.0",
        "org.apiguardian.api=org.apiguardian:apiguardian-api:1.0.0",
        "org.opentest4j=org.opentest4j:opentest4j:1.1.0" /*,*/

//        // JMH
//        "org.openjdk.jmh=org.openjdk.jmh:jmh-core:1.20",
//        "org.apache.commons.math3=org.apache.commons:commons-math3:3.6.1",
//        "net.sf.jopt-simple=net.sf.jopt-simple:jopt-simple:5.0.4",
//        "org.openjdk.jmh.generator=org.openjdk.jmh:jmh-generator-annprocess:1.20"
    )

// compiler.
//     rawArguments(
//         "--processor-module-path", "deps"   // enable JMH annotation processor
//     )

docer.
    quiet(true).
    link(uri("https://docs.oracle.com/javase/9/docs/api/"))
   
packager.
    modules(
        "fr.umlv.javaInside@1.0/fr.umlv.javaInside.Main"
    )   
    
run(resolver, modulefixer, compiler, tester, docer, packager, runner /*, perfer */)

/exit
