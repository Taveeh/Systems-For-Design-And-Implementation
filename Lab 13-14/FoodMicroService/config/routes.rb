Rails.application.routes.draw do
  get '/api/foods', to: 'food#index'
  post '/api/foods', to: 'food#create'
  put '/api/foods', to: 'food#update'
  delete '/api/foods/:id', to: 'food#delete'
  get '/api/foods/valid', to: 'food#valid'
end
