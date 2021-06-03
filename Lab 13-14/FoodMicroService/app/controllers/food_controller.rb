require 'json'
class FoodController < ApplicationController
  skip_before_action :verify_authenticity_token
  def index
    @foods = Food.all
    print(@foods)
    print("\n")
    render json: @foods
  end

  def show
    @food = Food.find(params[:id])
    render json: @food
  end

  def create
    @food = Food.create(
      name: params[:name],
      producer: params[:producer],
      expiration_date: params[:expirationDate]
    )
    @food.save
    render json: @food, status: :ok
  end

  def update
    @food = Food.find(params[:id])
    @food.update(
      name: params[:name],
      producer: params[:producer],
      expiration_date: params[:expirationDate]
    )
  end

  def delete
    @food = Food.find(params[:id])
    @food.destroy
  end

end
