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
1. Copy files in `wordworks-php` into a folder in your Apache HTTP Server working directory, e.g. `wordworks-ui` in `htdocs` for XAMPP.
2. Start your Apache Server.
3. Open your browser and go to `127.0.0.1/wordworks-ui/wordcloud.php`.
4. Now you're good to go.
