# Username Checker

## Purpose
I was helping a team of students and the team's product owner asked if we could check usernames for "appropriateness". I was inspired to try to use ChatGPT's OpenAI to do it. It brought me back to a time when I was creating student accounts for a college Unix administration position, and we had a guy named Steve Hitlin apply for an account. Our policy was to use first initial, followed by last name, so you can probably see the problem. This is how he ended up with the username sthitlin.

The fun part was how much I had to delve into **prompt engineering** to try to get the API to:

1. Only return "yes or no". The tool was definitely trying to offer explanations as to why it was judging usernames as potentially offensive.
1. Be strict. It's still less strict than I'd like but at least it rules out the obvious ones.

I have an OpenAI key, and to keep the key private, I set it as a Windows environment variable (I named mine **openAiApiKey**) on my local system. You can generally get a free trial key, though I set up a paid account. Thus far, I've used (literally) $0.06 worth of credits. I'd recommend you set a limit and protect your key.

### Files
|File|Description|
|-|-|
| OpenAiMain.Java|Driver program| 
| dao/OpenAiConfiguration.java|Set up the endpoint and headers|
| model/OpenAiRequest.java|Set up the request to the API|
| model/OpenAiResponse.java|Handle parsing of response|
