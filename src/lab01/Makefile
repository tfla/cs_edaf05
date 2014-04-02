# Example Makefile for automatic testing of lab solutions in EDAF05
#
# Run 'make test' or 'make testall' to perform the tests.


# Search for .out files:
# Change to make sure the path is where the testdata is stored on your system.
TESTDIR=/home/tfla/code/edaf05/lab01
OUTS=$(wildcard $(TESTDIR)/*.out)


test :
	java   GS  $(TESTDIR)/sm-kt-p-4.in | diff -q $(TESTDIR)/sm-kt-p-4.out -
	java   GS  $(TESTDIR)/sm-kt-p-5.in | diff -q $(TESTDIR)/sm-kt-p-5.out -	


testall :  $(OUTS:.out=.test)

%.test : %.in
	java  GS  $< | diff -q $*.out -
