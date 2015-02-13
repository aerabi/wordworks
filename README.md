# wordworks
WordWorks is a machine translation project, using BabelNet.

## Install
WordWorks consists of many sub-projects. Each of them must be built separately.

### WordWorks Java
1. First download BabelNet version 2.5, API and index, and also download WordNet 3.0.
2. Copy `wordworks-java` into your Eclipse workspace.
3. Copy `config` and `resources` from BabelNet API project into `wordworks-java`.
4. Extract BabelNet index.
5. Set `babelnet.dir` in `config/babelnet.var.properties` to your BabelNet index directory.
6. Set `jlt.wordnetPrefix` in `config/jlit.var.properties` to your WordNet directory address, without its version. (You can find more instructions in the file.)
7. Now you're good to go. You can run examples with Eclipse, or build the project with Maven.

### WordWorks PHP
First way, is easiest when you just want to use the code.
1. Copy files in `wordworks-php` into a folder in your Apache HTTP Server working directory, e.g. `wordworks-ui` in `htdocs` for XAMPP.
2. Start your Apache Server.
3. Open your browser and go to `127.0.0.1/wordworks-ui/wordcloud.php`.
4. Now you're good to go.

If you want to develop too, it is easier to do this.
For Windows, we assume that your project directory is `B:/Documents/Dev/Git/git/wordworks`.
1. Go to `xampp/apache/conf/extra/httpd-vhosts.conf`.
2. Uncomment line 19 (`NameVirtualHost *:80`).
3. Uncomment the block starting at line ~36, and change it to this:

	<VirtualHost *:80>
		DocumentRoot "B:/Documents/Dev/Git/git/wordworks"
		ServerName wordworks.localhost
		<Directory B:/Documents/Dev/Git/git/wordworks>
			Order allow,deny
			Allow from all
		</Directory>
	</VirtualHost>

4. Save it.
5. Go to `Windows/System32/drivers/etc/hosts`.
6. Add `127.0.0.1		wordworks.localhost` to the end of file (before the Spybot - Search & Destroy stuff if you have that installed).
7. Save it. (You might have to save it to the desktop, change the permissions on the old hosts file (right click > properties), and copy the new one into the directory over the old one (or rename the old one) if you are using Vista and have trouble).
8. Restart Apache.
9. Go to `localhost/wordworks-php`.
10. If it says `Access forbidden!`, go to `httpd.conf` and find `<Directory />`. Then replace the whole block with this:

	```
	<Directory />
		Options FollowSymLinks
		AllowOverride All
		Order deny,allow
		Allow from all
	</Directory>
	```

11. Restart Apache, and you're good to go.