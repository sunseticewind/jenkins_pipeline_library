FROM ubuntu:18.04

RUN apt-get update && apt-get -y upgrade && \
    apt-get -y install wget curl

RUN curl -sL https://deb.nodesource.com/setup_14.x | bash -

RUN apt-get update && apt-get -y install nodejs

RUN wget https://packages.microsoft.com/config/ubuntu/18.04/packages-microsoft-prod.deb -O packages-microsoft-prod.deb

RUN dpkg -i packages-microsoft-prod.deb

RUN apt-get update; \
    apt-get install -y apt-transport-https && \
    apt-get update && \
    apt-get install -y dotnet-sdk-3.1 aspnetcore-runtime-3.1 dotnet-runtime-3.1

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
