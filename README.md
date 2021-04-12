J'Bang! / Eclipse integration POC
===============================

Import [J'Bang!](https://github.com/jbangdev/jbang) scripts in Eclipse: Import Project... > JBang > JBang script

Currently expects to use JBang from `~/.sdkman/candidates/jbang/current/`. Yes it sucks, I know.

Cuurently supports 
- `//DEPS` / `@Grab` dependencies
- `//JAVA` version
- `//SOURCES` additional sources 


Build
-----

Open a terminal and execute:

    ./mvnw clean package
    
You can then install the generated update site from `jbang.eclipse.site/target/jbang.eclipse.site-<VERSION>-SNAPSHOT.zip`

License
-------
EPL 2.0, See [LICENSE](LICENSE) file.

