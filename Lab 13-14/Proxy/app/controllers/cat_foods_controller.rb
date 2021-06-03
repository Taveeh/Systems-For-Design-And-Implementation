require 'rest-client'
require 'json'

$cat_food_endpoint = 'http://catfoods:8080/web-1.0-SNAPSHOT/api/catFoods'
$headers = { "Accept" => "application/json", "Accept-Encoding" => "gzip, deflate", "Content-Type" => "application/json" }

class CatFoodsController < ApplicationController
  skip_before_action :verify_authenticity_token

  def get
    result = RestClient.get $cat_food_endpoint, $headers
    render json: { :catFoods => JSON.parse(result.body)["catFoods"] }
  end

  def add
    RestClient.post $cat_food_endpoint,
                    { catId: params[:catId], foodId: params[:foodId] },
                    $headers
  end

  def delete
    RestClient.delete $cat_food_endpoint + "/" + params[:catId] + "&" + params[:foodId],
                      $headers
  end

  def get_by_food
    result = RestClient.get $cat_food_endpoint + "/" + params[:foodId], $headers
    render json: {:catFoods => JSON.parse(result.body)["catFoods"]}
  end
end
