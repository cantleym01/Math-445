#include "queues.h"

std::queue<message> defaultQueue;

std::queue<message> incomingQueue;

std::queue<message > outgoingQueue;

std::queue<message> highPriorityQueue;

std::map <int, std::queue<message> > tempQueues;
int maxIndex;





