#ifndef QUEUES.H
#define QUEUES.H

#include <queue>
#include <vector>
#include <map>
#include "message.h"

//These are global variables inside of the server

/* the queue that all messages go through (except for high priority) */
extern std::queue<message> defaultQueue;

/* after a message has been pushed to a message reader, if the message is coming into the server for
processing, this is the message queue that it will go to after the default */
extern std::queue<message> incomingQueue;

/* after a message has been pushed to a message reader, if the message is to go to a client application
it will go through this queue after passing through the default */
extern std::queue<message> outgoingQueue;

/* if a queue cannot keep up with the amount of messages, the program will create a temporary queue here
and also spawn another thread for another message pusher and the two of them will work until completion
and then close themselves, the program will keep track of them by their indexes (has a max of 50)
I would have used an unordered_map, but my compiler does not support C++11 */
extern std::map <int, std::queue<message> > tempQueues;
extern int maxIndex;

#endif
