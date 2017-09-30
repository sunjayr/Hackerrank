/*------------------------------------------------------------
Program Name: EqualStacks.cpp

Problem: Given three different stacks each containing cylinders
of varying height, where the height of a stack can be changed
by removing the topmost cylinder any number of times, find the 
maximum possible height of the stacks such that all of the
stacks are exactly the same height.

Solution: Loop until one of the stacks is empty and pop off the
element from the heighest stack in each iteration of the loop.
After each removal check to see if the stacks have equal height. 
-------------------------------------------------------------*/
#include <map>
#include <set>
#include <list>
#include <cmath>
#include <ctime>
#include <deque>
#include <queue>
#include <stack>
#include <string>
#include <bitset>
#include <cstdio>
#include <limits>
#include <vector>
#include <climits>
#include <cstring>
#include <cstdlib>
#include <fstream>
#include <numeric>
#include <sstream>
#include <iostream>
#include <algorithm>
#include <unordered_map>

using namespace std;


int main(){
    //read from stdin into arrays
    int n1;
    int n2;
    int n3;
    cin >> n1 >> n2 >> n3;
    vector<int> h1(n1);
    for(int h1_i = 0;h1_i < n1;h1_i++){
       cin >> h1[h1_i];
    }
    vector<int> h2(n2);
    for(int h2_i = 0;h2_i < n2;h2_i++){
       cin >> h2[h2_i];
    }
    vector<int> h3(n3);
    for(int h3_i = 0;h3_i < n3;h3_i++){
       cin >> h3[h3_i];
    }
    
    //initialize stack heights
    int hs1 = 0;
    int hs2 = 0;
    int hs3 = 0;
    
    //initialize stacks
    stack<int> s1;
    stack<int> s2;
    stack<int> s3;
    
    //load disks into stacks increasing stack heights
    for(int i = n1-1; i >= 0; i--){
        s1.push(h1[i]);
        hs1 += h1[i];
    }
    for(int i = n2-1; i >= 0; i--){
        s2.push(h2[i]);
        hs2 += h2[i];
    }
    for(int i = n3-1; i >= 0; i--){
        s3.push(h3[i]);
        hs3 += h3[i];
    }
    
    bool heightReached = false;
    
    //loop until a stack is empty removing from the heighest stack and checking 
    //if all the stacks have the same height
    while(!s1.empty() && !s2.empty() && !s3.empty() ){
        if( hs1 == hs2 && hs1 == hs3){
            cout << hs1 << endl;
            heightReached = true;
            break;
        }
        
        int maximum = max(max(hs1,hs2),hs3);
        if( maximum == hs1 ){
            hs1 -= s1.top();
            s1.pop();
        }
        else if( maximum == hs2 ){
            hs2 -= s2.top();
            s2.pop();
        }
        else if( maximum == hs3){
            hs3 -= s3.top();
            s3.pop();
        }
    }
    if(!heightReached ){
        cout << 0 << endl;
    }
    return 0;
}