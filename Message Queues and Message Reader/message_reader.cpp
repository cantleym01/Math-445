#include <iostream>
#include "message_reader.h"
#include "queues.h"

message_reader::message_reader()
{
    inUseQuery = false;
    exit = false;
}

message_reader::~message_reader(){}

void message_reader::runReader() {
    /*commented out for testing

    //the thread will run for as long as the server is running
    while(exit!= true)
    {

    */
        //if the default queue has something to process, grab it and do it.
        //change to if when threads are implemented
        while (defaultQueue.size() > 0)
        {
            //get the message from the default queue
            messageBeingProcessed = defaultQueue.front();
            defaultQueue.pop();

            if (messageBeingProcessed.currentAction == "incoming") //incoming message
            {
                incomingQueue.push(messageBeingProcessed);
            }
            else if (messageBeingProcessed.currentAction == "outgoing") //outgoing message
            {
                outgoingQueue.push(messageBeingProcessed);
            }
            else
            {
                throw std::invalid_argument("error: Something has went wrong, the message is neither incoming nor outgoing...");
            }
        }
    //}
}  //this will be the function that the threads run

void message_reader::runTempReader(int queueId)
{
    //while the temp queue has something to process, grab it and do it.
    while (tempQueues[queueId].size() > 0)
    {
        //get the message from the temp queue
        messageBeingProcessed = tempQueues[queueId].front();
        tempQueues[queueId].pop();

        if (messageBeingProcessed.currentAction == "incoming") //incoming message
        {
            incomingQueue.push(messageBeingProcessed);
        }
        else if (messageBeingProcessed.currentAction == "outgoing") //outgoing message
        {
            outgoingQueue.push(messageBeingProcessed);
        }
        else
        {
            throw std::invalid_argument("error: Something has went wrong, the message is neither incoming nor outgoing...");
        }
    }
} //this will run a new reader that reads only 1 queue and will close when finished
 //the parameter passed in is the index of the queue it needs to run from the array of temp queues
