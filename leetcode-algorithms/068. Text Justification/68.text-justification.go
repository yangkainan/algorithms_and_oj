package main

import (
	"fmt"
	"github.com/google/go-cmp/cmp"
)

type line struct {
	isLastLine    bool
	lineSize      int
	availableSize int
	words         []string
	numberOfSpace int
	wordIntervalWidth int
}

func (l *line) print() string {
	result := ""

	if l.isLastLine {

		for _, word := range l.words {
			result += word
			if len(result) < l.lineSize {
				result += " "
			}
		}

		for ; len(result) < l.lineSize; {
			result += " "
		}

		return result
	}

	numberOfWords := len(l.words);
	numberOfSpace := l.numberOfSpace;
	wordInterval := l.wordIntervalWidth;

	remainingSpace := numberOfSpace - (wordInterval * (numberOfWords - 1))

	for _, word := range l.words {
		result += word;
		for i := 0; i < wordInterval && numberOfSpace > 0; i++ {
			result += " "
			numberOfSpace--
		}

		if remainingSpace > 0 {
			result += " "
			remainingSpace--
			numberOfSpace--

		}
	}

	for ; numberOfSpace > 0; numberOfSpace-- {
		result += " "
	}

	return result
}

func (l *line) addWord(word string) {

	l.words = append(l.words, word)
	l.availableSize -= len(word) + 1
	l.numberOfSpace -= len(word)

	if len(l.words) > 1 {
		l.wordIntervalWidth = l.numberOfSpace/ (len(l.words) - 1)
	}

}

func makeNewLine(width int) *line{

	return &line{
		isLastLine:    false,
		lineSize:      width,
		availableSize: width,
		words:         make([]string, 0, 0),
		numberOfSpace: width,
		wordIntervalWidth: 0,
	}

}

func fullJustify(words []string, maxWidth int) []string {

	lines := make([]*line, 0, 0)

	currentLine := makeNewLine(maxWidth)

	lines = append(lines, currentLine)
	for _, word := range words {
		if len(word) > maxWidth {
			panic(fmt.Sprintf("Word:%s exceed maxWidth:%d", word, maxWidth))
		}

		if currentLine.availableSize < len(word) {
			currentLine = makeNewLine(maxWidth)

			lines = append(lines, currentLine)
		}
		currentLine.addWord(word)

	}
	currentLine.isLastLine = true



	result := make([]string, 0, 0)

	for _, tmp := range lines {
		result = append(result, (*tmp).print())
	}

	return result

}

func main() {
	tests := map[string]struct {
		input struct {
			words []string
			width int
		}
		want []string
	}{
		"simple": {input: struct {
			words []string
			width int
		}{words: []string{"What", "must", "be", "acknowledgment", "shall", "be"}, width: 16},
			want: []string{"What   must   be", "acknowledgment  ", "shall be        "}},
		"second": {input: struct {
			words []string
			width int
		}{words: []string{"This", "is", "an", "example", "of", "text", "justification."}, width: 16},
			want: []string{"This    is    an", "example  of text", "justification.  "}},
		"third": {input: struct {
			words []string
			width int
		}{words: []string{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain",
			"to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"}, width: 20},
			want: []string{"Science  is  what we", "understand      well", "enough to explain to", "a  computer.  Art is", "everything  else  we", "do                  "}},
	}

	for name, tc := range tests {
		got := fullJustify(tc.input.words, tc.input.width)
		diff := cmp.Diff(tc.want, got)
		if diff != "" {
			fmt.Printf("name: %v, input: %v, want: %v, got: %v, diff: %v",
				name, tc.input, tc.want, got, diff)
		}

	}

}
