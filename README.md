## wordworks
WordWorks is a machine translation project, using BabelNet.

# Install
1. First download BabelNet version 2.5, API and index, and also download WordNet 3.0.
2. Pull a fresh copy of master branch into your Eclipse workspace.
3. Copy `config` and `resources` from BabelNet API project into your working directory.
4. Extract BabelNet index.
5. Set `babelnet.dir` in `config/babelnet.var.properties` to your BabelNet index directory.
6. Set `jlt.wordnetPrefix` in `config/jlit.var.properties` to your WordNet directory address, without its version. (You can find more instructions in the file.)
7. Now you're good to go.
