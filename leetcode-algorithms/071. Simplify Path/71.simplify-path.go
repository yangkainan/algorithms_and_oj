package main

import (
	"errors"
	"fmt"
	"github.com/google/go-cmp/cmp"
	"strings"
)

/*

以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。

在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径

请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。



示例 1：

输入："/home/"
输出："/home"
解释：注意，最后一个目录名后面没有斜杠。


示例 2：

输入："/../"
输出："/"
解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。


示例 3：

输入："/home//foo/"
输出："/home/foo"
解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。


示例 4：

输入："/a/./b/../../c/"
输出："/c"


示例 5：

输入："/a/../../b/../c//.//"
输出："/c"


示例 6：

输入："/a//b////c/d//././/.."
输出："/a/b/c"

*/



func main() {
	tests := map[string]struct {
		input string
		want  string
	}{
		"/home/":       {input: "/home/", want: "/home"},
		"/../":       {input: "/../", want: "/"},
		"/home//foo/":       {input: "/home//foo/", want: "/home/foo"},
		"/a/./b/../../c/":       {input: "/a/./b/../../c/", want: "/c"},
		"/a/../../b/../c//.//":       {input: "/a/../../b/../c//.//", want: "/c"},
		"/a//b////c/d//././/..":       {input: "/a//b////c/d//././/..", want: "/a/b/c"},
	}

	for name, tc := range tests {
		got := simplifyPath(tc.input)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}

type stack struct {
	elements []string
}

func(s *stack) push(e string) {
	s.elements = append(s.elements, e)
}

func (s *stack) pop() (string, error) {

	curLen := len(s.elements)
	if curLen <= 0 {
		return "", errors.New("empty")
	}
	elem := s.elements[curLen - 1]

	s.elements = s.elements[:(curLen - 1)]

	return elem,nil

}

func (s *stack) toString() string{
	result := "/"
	for _, ch := range s.elements {
		result += ch
		result += "/"
	}

	if len(result) > 1 && strings.HasSuffix(result, "/") {
		result = strings.TrimSuffix(result, "/")
	}

	return result
}


func simplifyPath(path string) string {

	internalStack := &stack{}


	for _, val := range strings.Split(path, "/") {
		switch val {
		case "..":
				internalStack.pop()
		case "":
		case ".":

		default:
			internalStack.push(val)

		}

	}

	return internalStack.toString()
    
}
