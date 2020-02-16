Spigot Update Checker
=====================

Check for updates to a Spigot plugin.

Usage
-----

Add the UpdateChecker package as a dependency to your plugin
(or any other project).
Example for Maven, using the author's personal repo:

```xml
<dependencies>
	<dependency>
		<groupId>net.ssterling</groupId>
		<artifactId>updatechecker</artifactid>
		<version>1.0.0</version> <!-- or whatever -->
		<scope>compile</scope>
	</dependency>
</dependencies>

<repositories>
	<repository>
		<id>ssterling.net</id>
		<url>https://www.ssterling.net/comp/maven-repo</url>
	</repository>
</repositories>
```

Next, create a new `UpdateChecker` object.
The first variable is the project ID of your plugin (found following a `.`
in the project URL), and the second is the current version, attainable
using `YourPlugin.getDescription().getVersion()`.
Method `check()` will return the new version as a string if it exists,
or a null value if there is no new version.
Example for
[BukkitGreentext](https://www.spigotmc.org/resources/bukkitgreentext.55295/):

```java
import net.ssterling.updatechecker;

/* The following code would obviously be inside a method: */

String project_id = "55295";
String current_version = this.getDescription().getVersion();

UpdateChecker update_checker = new UpdateChecker(project_id, current_version);
try {
	String new_version = update_checker.check();
	if (new_version != null) {
		this.getLogger().info("Found new version " + new_version);
	} else {
		this.getLogger().info("No new version found");
	}
} catch (Throwable ex) {
	/* something happened */
	this.getLogger().info("Failed to check for updates");
}
```

If this documentation isn't clear enough (probably my fault),
[open an issue](https://www.gitlab.com/ssterling/updatechecker/issues).

Notes
-----

Some to-do:

* Maybe a scheduler to check for updates at regular intervals

Contributing
------------

Tab characters for indentation; spaces for alignment.
Just look through the code and assimilate it.
