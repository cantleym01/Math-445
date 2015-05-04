#ifndef MESSAGE.H
#define MESSAGE.H

#include <string>
#include <stdio.h>
#include <stdlib.h>

//This is the message format that will be used on all messages in the project

class message
{
public:
    std::string divider; //the unique divider that seperates message parts
    std::string sender; //sender of the message
    std::string recipient; //recipient (can be entire channel for incoming messages)
    std::string text; //the contents of the message (limit of 255 char)
    std::string channel; //the name of the channel the message needs to go to
    std::string currentAction; //tells if the message is incoming or outgoing

    message()
    {
        divider = "$£XxX£$";
    }

    std::string buildMessage()
    {
        std::string messageStr = channel + currentAction + sender + recipient + text;
        return "";
    }
};

#endif

