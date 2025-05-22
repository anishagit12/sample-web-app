#building the app
FROM node:18 AS build

#set the working directory
WORKDIR /app

#copy package.json and package-lock.json
COPY package*.json ./

#install dependencies
RUN npm install

#copy the source code
COPY . .

#Build the angular app in production mode
RUN npm run build -- --configuration production --project FullStackCrudAppFrontend

#serving the app using nginx
FROM nginx:alpine

#copy the angular build output to the nginx server directory
COPY --from=build /app/dist/full-stack-crud-app-frontend /usr/share/nginx/html

#copy nginx.conf /etc/nginx/nginx.conf
EXPOSE 80

#run Nginx in the foreground
CMD ["nginx", "-g", "daemon off;"]