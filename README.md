# Textreplace project
The textreplace program take an input file and apply a group of replacements (csv format).

## Compile & Package
Run `mvn insall`

## Execute
Run `java -jar textreplace-jar-with-dependencies.jar` with following options
```
 -e (--espace) C                  : escape character (default: \)
 -i (--input) INPUT               : input file
 -o (--output) OUTPUT             : output file (default: output.txt)
 -q (--quote) C                   : quote character (default: ")
 -r (--replacements) REPLACEMENTS : replacement file
 -s (--separator) C               : separator character (default: ,)
```

## Example
Here is a example for replacing text from a SSA formated text to an SRT formated text.

Input file :
```
I am not\namused.
I am not\Namused.
I am {\b1}not{\b0} amused.
I am {\i1}not{\i0} amused.
I am {\u1}not{\u0} amused.
I am {\b1}{\i1}not{\i0}{\b0} amused.
I am  not  amused.
I am not,amused.
I am not.Amused
I am not amused!
I am not amused?
{\pos(255.255)}I am not amused.
```

Replacements file (findwhat, replacewith, casesensitive & regex columns are mandatory) :
```
findwhat;replacewith;casesensitive;regex;comment
\{\\b.+?\}(.+?)\{\\b0\};<b>$1</b>;false;true;Replace bold tag
\{\\i.+?\}(.+?)\{\\i0\};<i\>$1</i>;false;true;Replace italic tag
\{\\u.+?\}(.+?)\{\\u0\};<u\>$1</u>;false;true;Replace underline tag
  ; ;false;false;Clean double spaces
,(\w);, $1;false;true;Force space after comma
\.(\w);. $1;false;true;Force space after dot
(\w)\?;$1 ?;false;true;Force space before question mark
(\w)\!;$1 !;false;true;Force space before exclamation mark
\{.+?\};;false;true;Ignore all other tags
\\n;"
";false;true;Line break
```

Running with `java -jar textreplace-jar-with-dependencies.jar -i input.txt -r replacements.txt -s ; -e ¥`

Processed output file :
```
I am not
amused.
I am not
amused.
I am <b>not</b> amused.
I am <i>not</i> amused.
I am <u>not</u> amused.
I am <b><i>not</i></b> amused.
I am not amused.
I am not, amused.
I am not. Amused
I am not amused !
I am not amused ?
I am not amused.
```
