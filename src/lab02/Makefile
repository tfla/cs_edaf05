# Example Makefile for automatic testing of lab solutions in EDAF05
#
# Run 'make testall' to perform the tests.


# Search for .out files:
# Change to make sure the path is where the testdata is stored on your system.
TESTDIR=/home/tfla/code/edaf05/lab02
OUTS=$(wildcard $(TESTDIR)/*.out)

testall :  $(OUTS:.out=.test)

%.test : %.dat %.in
	java  WordLadders  $^ | diff -q $*.out -
