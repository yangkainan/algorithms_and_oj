package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
	"strings"
)


var _cache map[string]bool = make(map[string]bool)
var _keyDel = "_(&*&^&^&^&"



func isScramble(s1 string, s2 string) bool {
	if s1 == "" || s2 == "" {
		return false
	}

	if s1 == s2 {
		return true
	}

	if res, ok := _cache[s1 + _keyDel + s2]; ok {
		return res
	}



	if len(s1) != len(s2) {
		return false
	}

	if len(s1) == 2 {
		var arr1 = strings.Split(s1, "")
		var arr2 = strings.Split(s2, "")
		var res = arr1[0] == arr2[1] && arr1[1] == arr2[0]

		_cache[s1+_keyDel+s2] = res
		return res

	}

	for i := 1; i < len(s1); i++ {
		var subS1Left = s1[0:i]
		var subS1Right = s1[i:]

		var subS2Left = s2[0:i]
		var subS2Right = s2[i:]


		if (isScramble(subS1Left, subS2Left) && isScramble(subS1Right, subS2Right)) ||
			(isScramble(subS1Left, subS2Right) && isScramble(subS1Right, subS2Left)) {

			_cache[s1+_keyDel+s2] = true
			return true
		}

		subS2Left = s2[0:(len(s2)-i)]
		subS2Right = s2[(len(s2)-i):]

		if (isScramble(subS1Left, subS2Left) && isScramble(subS1Right, subS2Right)) ||
			(isScramble(subS1Left, subS2Right) && isScramble(subS1Right, subS2Left)) {

			_cache[s1+_keyDel+s2] = true
			return true
		}


	}


	_cache[s1+_keyDel+s2] = false

	return false
}

func main() {
	tests := map[string]struct {
		input []string
		want  bool
	}{
		//"great, rgeat":       {input: []string{"great", "rgeat"}, want: true},
		//"abcde, caebd":       {input: []string{"abcde", "caebd"}, want: false},
		"abb, bba":       {input: []string{"abb", "bba"}, want: true},
	}

	for name, tc := range tests {
		got := isScramble(tc.input[0], tc.input[1])
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
