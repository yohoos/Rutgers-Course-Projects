# mira.py
# -------
# Licensing Information: Please do not distribute or publish solutions to this
# project. You are free to use and extend these projects for educational
# purposes. The Pacman AI projects were developed at UC Berkeley, primarily by
# John DeNero (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# For more info, see http://inst.eecs.berkeley.edu/~cs188/sp09/pacman.html

# Mira implementation
import util
PRINT = True

class MiraClassifier:
  """
  Mira classifier.

  Note that the variable 'datum' in this code refers to a counter of features
  (not to a raw samples.Datum).
  """
  def __init__( self, legalLabels, max_iterations):
    self.legalLabels = legalLabels
    self.type = "mira"
    self.automaticTuning = False
    self.C = 0.001
    self.legalLabels = legalLabels
    self.max_iterations = max_iterations
    self.initializeWeightsToZero()

  def initializeWeightsToZero(self):
    "Resets the weights of each label to zero vectors"
    self.weights = {}
    for label in self.legalLabels:
      self.weights[label] = util.Counter() # this is the data-structure you should use

  def train(self, trainingData, trainingLabels, validationData, validationLabels):
    "Outside shell to call your method. Do not modify this method."

    self.features = trainingData[0].keys() # this could be useful for your code later...

    if (self.automaticTuning):
        Cgrid = [0.002, 0.004, 0.008]
    else:
        Cgrid = [self.C]

    return self.trainAndTune(trainingData, trainingLabels, validationData, validationLabels, Cgrid)

  def trainAndTune(self, trainingData, trainingLabels, validationData, validationLabels, Cgrid):
    """
    This method sets self.weights using MIRA.  Train the classifier for each value of C in Cgrid,
    then store the weights that give the best accuracy on the validationData.

    Use the provided self.weights[label] data structure so that
    the classify method works correctly. Also, recall that a
    datum is a counter from features to values for those features
    representing a vector of values.
    """
    "*** YOUR CODE HERE ***"
    top_weights = None
    top_acc = None

    for C_value in Cgrid:
        c_weights = self.weights.copy() # allows each iteration of C_value to start with original weights
        for iteration in xrange(self.max_iterations):
            print "Starting iteration ", iteration, "..."
            for i in xrange(len(trainingData)):
                High_Score = None
                Best_Label = None
                Datum = trainingData[i]

                for label in self.legalLabels:
                    current_score = Datum * c_weights[label] # score of correctness, higher score means better classificiation
                    if High_Score is None or current_score > High_Score:
                        High_Score = current_score
                        Best_Label = label

                real_label = trainingLabels[i] # find truth label

                if Best_Label != real_label: # update phase using Tau
                    T = min(C_value, ((c_weights[Best_Label] - c_weights[real_label]) * Datum + 1.0) / (2.0 * (Datum * Datum)))
                    T_datum = Datum.copy()
                    T_datum.divideAll(1.0 / T) # multiply datum by Tau

                    c_weights[real_label] += T_datum # pushes weight vector closer to being correct
                    c_weights[Best_Label] -= T_datum # pushes incorrect weight vector away from being correct

        trained_labels = self.classify(validationData)
        is_true = 0
        # checks for accuracy and updates top_weights based on accuracy
        for i in xrange(len(trained_labels)):
            label = trained_labels[i]
            if validationLabels[i] == label:
                is_true += 1
        acc = is_true / len(trained_labels)

        if top_acc is None or acc > top_acc:
            top_weights = c_weights
            top_acc = acc

    self.weights = top_weights
  def classify(self, data ):
    """
    Classifies each datum as the label that most closely matches the prototype vector
    for that label.  See the project description for details.

    Recall that a datum is a util.counter...
    """
    guesses = []
    for datum in data:
      vectors = util.Counter()
      for l in self.legalLabels:
        vectors[l] = self.weights[l] * datum
      guesses.append(vectors.argMax())
    return guesses


  def findHighOddsFeatures(self, label1, label2):
    """
    Returns a list of the 100 features with the greatest difference in feature values
                     w_label1 - w_label2

    """
    featuresOdds = []

    "*** YOUR CODE HERE ***"

    return featuresOdds
