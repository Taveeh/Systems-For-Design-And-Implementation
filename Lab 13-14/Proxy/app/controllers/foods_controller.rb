require 'rest-client'
require 'json'
$foods_endpoint = 'http://foods:3001/api/foods'

class FoodsController < ApplicationController
  skip_before_action :verify_authenticity_token

  def index
  end

  def foods
    print("WOAAH WE'RE HALFWAY THERE\n")
    response = RestClient.get $foods_endpoint, { "Accept" => "application/json", "Accept-Encoding" => "gzip, deflate" }
    print(response.body)
    print("\n")
    print(response.body.class)
    print("\n")
    render json: {:foods => JSON.parse(response.body) }
  end

  def add
    print("WOAAH WE'RE ADDING HERE\n")
    print(params)
    print("\n")
    RestClient.post $foods_endpoint, { :name => params[:name], :producer => params[:producer], :expirationDate => params[:expirationDate] }, { "Accept" => "application/json", "Accept-Encoding" => "gzip, deflate" }
  end

  def update
    RestClient.put $foods_endpoint, {:id => params[:id], :name => params[:name], :producer => params[:producer], :expirationDate => params[:expirationDate]}, { "Accept" => "application/json", "Accept-Encoding" => "gzip, deflate" }
  end

  def delete
    RestClient.delete $foods_endpoint + '/' + params[:id], { "Accept" => "application/json", "Accept-Encoding" => "gzip, deflate" }
  end

end
