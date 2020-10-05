public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    final static double G=6.67e-11;
    
    public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
                  xxPos=xP;
                  yyPos=yP;
                  xxVel=xV;
                  yyVel=yV;
                  mass=m;
                  imgFileName=img;
              }

    public Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }

    public double calcDistance(Planet p2) {
        double dx= this.xxPos-p2.xxPos;
        double dy=this.yyPos-p2.yyPos;
        return Math.sqrt(dx*dx+dy*dy);
    }

    public double calcForceExertedBy(Planet p2) {
        return G*this.mass*p2.mass/(this.calcDistance(p2)*this.calcDistance(p2));
    }

    public double calcForceExertedByX(Planet p2) {
        return this.calcForceExertedBy(p2)*(p2.xxPos-this.xxPos)/this.calcDistance(p2);
    }

    public double calcForceExertedByY(Planet p2) {
        return this.calcForceExertedBy(p2)*(p2.yyPos-this.yyPos)/this.calcDistance(p2);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double sum=0;
        for(Planet p : allPlanets){
            if(!this.equals(p)){
                sum+=this.calcForceExertedByX(p);
            }
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double sum=0;
        for(Planet p : allPlanets){
            if(!this.equals(p)){
                sum+=this.calcForceExertedByY(p);
            }
        }
        return sum;
    }

    public void update(double dt, double fX, double fY) {
        double ax=fX/this.mass;
        double ay=fY/this.mass;
        this.xxVel=this.xxVel+(dt*ax);
        this.yyVel=this.yyVel+(dt*ay);
        this.xxPos=this.xxPos+dt*this.xxVel;
        this.yyPos=this.yyPos+dt*this.yyVel;
    }

    public  void draw() {
         StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }
}
