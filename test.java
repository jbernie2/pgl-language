import java.util.Random; 
public class test
{
	public static Random rand = new Random();
	public static void main(String args[])
	{
Expr();System.out.println();	}private static void zero() { System.out.print("0");}private static void nine() { System.out.print("9");}private static void eight() { System.out.print("8");}private static void seven() { System.out.print("7");}private static void six() { System.out.print("6");}private static void five() { System.out.print("5");}private static void four() { System.out.print("4");}private static void three() { System.out.print("3");}private static void two() { System.out.print("2");}private static void one() { System.out.print("1");}private static void rp() { System.out.print(")");}private static void lp() { System.out.print("(");}private static void div() { System.out.print("/");}private static void mul() { System.out.print("*");}private static void sub() { System.out.print("-");}private static void add() { System.out.print("+");}private static void z() { System.out.print("z");}private static void y() { System.out.print("y");}private static void x() { System.out.print("x");}private static void Expr()
{
int randNum = rand.nextInt(100);if(randNum < 100) {term();ops();term();}}private static void term()
{
int randNum = rand.nextInt(70);if(randNum < 20) {num();}else if(randNum < 30) {x();}else if(randNum < 40) {y();}else if(randNum < 50) {z();}else if(randNum < 70) {lp();Expr();rp();}}private static void ops()
{
int randNum = rand.nextInt(4);if(randNum < 1) {add();}else if(randNum < 2) {sub();}else if(randNum < 3) {mul();}else if(randNum < 4) {div();}}private static void num()
{
int randNum = rand.nextInt(30);if(randNum < 10) {digit();}else if(randNum < 30) {digit();num();}}private static void digit()
{
int randNum = rand.nextInt(10);if(randNum < 1) {one();}else if(randNum < 2) {two();}else if(randNum < 3) {three();}else if(randNum < 4) {four();}else if(randNum < 5) {five();}else if(randNum < 6) {six();}else if(randNum < 7) {seven();}else if(randNum < 8) {eight();}else if(randNum < 9) {nine();}else if(randNum < 10) {zero();}}}