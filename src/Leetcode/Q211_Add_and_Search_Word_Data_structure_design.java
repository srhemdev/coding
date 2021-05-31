package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbhatnagar2 on 1/15/17.
 */
public class Q211_Add_and_Search_Word_Data_structure_design {
    private TrieNode root = new TrieNode();

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {

            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }

            curr = curr.children.get(c);
        }
        curr.isLeaf = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfsSearch(root, word, 0);
    }

    private boolean dfsSearch(TrieNode root, String word, int start) {
        if(start == word.length()) {
            return root.isLeaf;
        }

        char c = word.charAt(start);

        if(root.children.containsKey(c)) {
            return dfsSearch(root.children.get(c), word, start+1);
        }
        else if(c == '.') {
            boolean result = false;
            for(Character ch: root.children.keySet()) {

                //if any path is true, set result to be true;
                if(dfsSearch(root.children.get(ch), word, start+1)) {
                    result = true;
                }
            }
            return result;
        }
        else {
            return false;
        }
    }

    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isLeaf;

        public TrieNode() {
            this.isLeaf = false;
            this.children = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        Q211_Add_and_Search_Word_Data_structure_design sol = new Q211_Add_and_Search_Word_Data_structure_design();
        sol.addWord("bad");
        sol.addWord("dad");
        sol.addWord("mad");
        boolean result1 = sol.search("pad");
        boolean result2 = sol.search("bad");
        boolean result3 = sol.search(".ad");
        boolean result4 = sol.search("b..");

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
    }
}
