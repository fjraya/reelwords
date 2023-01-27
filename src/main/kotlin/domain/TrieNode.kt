package domain

data class TrieNode(val value: String, val children: HashMap<String, TrieNode> = hashMapOf(), var lastLetter: Boolean = false) {
    fun hasChar(c: Char) = children[c.toString()] != null
    fun addChar(c: Char) {
        children[c.toString()] = TrieNode(c.toString())
    }

    fun removeChar(c:Char) {
        children.remove(c.toString())
    }

    fun getNode(c: Char) = children[c.toString()]
    fun hasNoChild(): Boolean = children.isEmpty()

}