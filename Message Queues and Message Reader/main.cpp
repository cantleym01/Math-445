#include <iostream>
#include <pthread.h>
#include "message.h"
#include "message_reader.h"
#include "queues.h"

using namespace std;

void testQueues();

int main()
{
    pthread_t test;
    if(pthread_create(test, NULL, testQueues, NULL) == -1)
    {
        cout << "bad";
    }

}

void testQueues()
{
    //reader
    message_reader reader;

    //test data
    message test0;
    test0.currentAction = "incoming";
    test0.text = "Hello from test0";

    message test1;
    test1.currentAction = "outgoing";
    test1.text = "Hello from test1";

    message test2;
    test2.currentAction = "incoming";
    test2.text = "Hello from test2";

    message test3;
    test3.currentAction = "outgoing";
    test3.text = "Hello from test3";

    message test4;
    test4.currentAction = "incoming";
    test4.text = "Hello from test4";

    message test5;
    test5.currentAction = "outgoing";
    test5.text = "Hello from test5";

    message test6;
    test6.currentAction = "incoming";
    test6.text = "Hello from test6";

    message ary[7] = {test0, test1, test2, test3, test4, test5, test6};

    for (int i = 0; i < 7; i++)
    {
    	defaultQueue.push(ary[i]);
    }

    reader.runReader();

    cout << "The incoming queue holds: " << incomingQueue.size() << endl;
    cout << "The outgoing queue holds: " << outgoingQueue.size() << endl;
}
