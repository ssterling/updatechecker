/*
 * UpdateChecker - check for updates to a Spigot plugin
 *
 * Copyright 2020 Seth Price
 * All rights reserved.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */
package net.ssterling.UpdateChecker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.maven.artifact.versioning.ComparableVersion;

/**
 * @author    Seth Price <ssterling AT firemail DOT cc>
 * @version   1.0
 * @since     1.0
 */
public class UpdateChecker
{
	private static String project_id;
	private static String current_version;

	/**
	 * @param project_id	The project ID of the plugin, found in the project URL.
	 * @param current_version	The current version of the plugin.
	 */
	public UpdateChecker(String project_id, String current_version)
	{
		this.project_id = project_id;
		this.current_version = current_version;
	}

	/**
	 * Checks for updates to the plugin.
	 *
	 * @return new version if update exists, null if otherwise
	 * @since 1.0
	 */
	public static String check() throws Throwable
	{
		URL update_url = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + project_id);
		URLConnection update_conn = update_url.openConnection();
		String new_version = new BufferedReader(new InputStreamReader(update_conn.getInputStream())).readLine();
		return versionIsHigher(new_version, current_version) ? new_version : null;
	}

	/**
	 * Determines whether a given version is higher than another.
	 *
	 * @param version_a_str	First version string to compare.
	 * @param version_b_str	Second version string to compare.
	 * @return true if first version string is higher, false if otherwise
	 * @since 1.0
	 */
	private static boolean versionIsHigher(String version_a_str, String version_b_str)
	{
		ComparableVersion version_a = new ComparableVersion(version_a_str);
		ComparableVersion version_b = new ComparableVersion(version_b_str);

		return version_a.compareTo(version_b) > 0 ? true : false;
	}
}
