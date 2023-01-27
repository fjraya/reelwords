package domain

class Trie {
    private val root: TrieNode = TrieNode("^", hashMapOf());

    fun insert(s: String) {

        var node = root
        s.lowercase().forEach {
            if (!node.hasChar(it)) node.addChar(it)
            node = node.getNode(it)!!
        }
        node.lastLetter = true
    }

    private fun innerSearch(s: String, index: Int, node: TrieNode): Boolean {
        if (s.isEmpty()) return true
        val charToSearch = s[index]
        if (!node.hasChar(charToSearch)) return false
        if (index == s.length - 1) return node.getNode(charToSearch)!!.lastLetter
        return innerSearch(s, index + 1, node.getNode(charToSearch)!!)
    }

    fun search(s: String) = innerSearch(s.lowercase(), 0, root)


    private fun innerDelete(s: String, index: Int, node: TrieNode): TrieNode {
        if (index == s.length - 1) {
            val actualNode = node.getNode(s[index])!!
            if (actualNode.lastLetter) actualNode.lastLetter = false
            if (actualNode.hasNoChild()) node.removeChar(s[index])
            return node
        }
        node.children[s[index].toString()] = innerDelete(s, index + 1, node.getNode(s[index])!!)
        if ((node.getNode(s[index])!!.hasNoChild()) && (!node.getNode(s[index])!!.lastLetter))
            node.removeChar(s[index])
        return node
    }

    fun delete(s: String): Boolean {
        if (s.isEmpty()) return true
        if (!search(s.lowercase())) return false
        innerDelete(s.lowercase(), 0, root)
        return true
    }


}

