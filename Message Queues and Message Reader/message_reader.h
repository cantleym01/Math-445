#ifndef MESSAGE_READER.H
#define MESSAGE_READER.H

#include <string>
#include <stdexcept>
#include "queues.h"
#include "message.h"

//This class will handle pushing the messages sent to it to the incoming or outgoing message queues

class message_reader
{
public:
    bool inUseQuery; //is this thread in use?
    bool exit; //does the thread need to shut down safely?
    int tempQueueId; //if the message reader is using a tempQueue, it needs the id to find it in the hashtable
    message messageBeingProcessed; //the message that is currently being processed

    message_reader();
    ~message_reader();
    void runReader(); //this will be the function that the threads run
    void runTempReader(int queueId); //this will run a new reader that reads only 1 queue and will close when finished
                                        //the parameter passed in is the key of the queue it needs to run from the map of temp queues
};

#endif


