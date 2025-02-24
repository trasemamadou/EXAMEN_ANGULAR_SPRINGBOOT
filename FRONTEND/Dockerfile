FROM node:18 AS build-stage
WORKDIR /app
COPY package*.json ./
#RUN npm install
COPY . .
RUN npm run build --prod
FROM nginx:latest AS production-stage
COPY --from=build-stage /app/dist/angular-crudapplication /usr/share/nginx/html
#ng COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
