require 'rest-client'

$cats_endpoint = 'http://cats:8080/web-1.0-SNAPSHOT/api/cats'
$headers = { "Accept" => "application/json", "Accept-Encoding" => "gzip, deflate", "Content-Type" => "application/json" }

class CatsController < ApplicationController
  skip_before_action :verify_authenticity_token
  def index
    "Hello Mr Cat"
  end

  def cats
    response = RestClient.get $cats_endpoint
    print(response.body)
    print("\n")
    render json:{:cats => JSON.parse(response.body)["cats"]}
  end

  def add
    RestClient.post $cats_endpoint,
                    {name: params[:name], breed: params[:breed], catYears: params[:catYears]}, $headers
  end

  def update
    RestClient.put $cats_endpoint + "/" + params[:id],
                   {name: params[:name], breed: params[:breed], catYears: params[:catYears]},
                   $headers
  end

  def delete
    RestClient.delete $cats_endpoint + "/" + params[:id]
  end
end
