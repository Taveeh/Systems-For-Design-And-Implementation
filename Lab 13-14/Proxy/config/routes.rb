Rails.application.routes.draw do
  get "/api/cats", to: "cats#cats"
  post "/api/cats", to: "cats#add"
  put "/api/cats/:id", to: "cats#update"
  delete "/api/cats/:id", to: "cats#delete"

  get "/api/food", to: "foods#foods"
  post '/api/food', to: "foods#add"
  put '/api/food/:id', to: "foods#update"
  delete '/api/food/:id', to: "foods#delete"

  get "/api/customers", to: "customers#customers"
  post "/api/customers", to: "customers#add"
  put "/api/customers/:id", to: "customers#update"
  delete "/api/customers/:id", to: "customers#delete"

  get "/api/catFoods", to: "cat_foods#get"
  post "/api/catFoods", to: "cat_foods#add"
  delete "/api/catFoods/:catId&:foodId", to: "cat_foods#delete"
  get "/api/catFoods/:foodId", to: "cat_foods#get_by_food"

  get "/api/purchases", to: "purchases#purchases"
  post "/api/purchases", to: "purchases#add"
  get "/api/purchases/minReview=:review", to: "purchases#min_review"
  delete "/api/purchases/:catId&:customerId", to: "purchases#delete"
  # For details on the DSL available within this file, see https://guides.rubyonrails.org/routing.html
end
