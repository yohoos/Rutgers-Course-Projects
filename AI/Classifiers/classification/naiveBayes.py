# naiveBayes.py
# -------------
# Licensing Information: Please do not distribute or publish solutions to this
# project. You are free to use and extend these projects for educational
# purposes. The Pacman AI projects were developed at UC Berkeley, primarily by
# John DeNero (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# For more info, see http://inst.eecs.berkeley.edu/~cs188/sp09/pacman.html

import util
import classificationMethod
import math

class NaiveBayesClassifier(classificationMethod.ClassificationMethod):
  """
  See the project description for the specifications of the Naive Bayes classifier.

  Note that the variable 'datum' in this code refers to a counter of features
  (not to a raw samples.Datum).
  """
  def __init__(self, legalLabels):
    self.legalLabels = legalLabels
    self.type = "naivebayes"
    self.k = 1 # this is the smoothing parameter, ** use it in your train method **
    self.automaticTuning = False # Look at this flag to decide whether to choose k automatically ** use this in your train method **

  def setSmoothing(self, k):
    """
    This is used by the main method to change the smoothing parameter before training.
    Do not modify this method.
    """
    self.k = k

  def train(self, trainingData, trainingLabels, validationData, validationLabels):
    """
    Outside shell to call your method. Do not modify this method.
    """

    # might be useful in your code later...
    # this is a list of all features in the training set.
    self.features = list(set([ f for datum in trainingData for f in datum.keys() ]));

    if (self.automaticTuning):
        kgrid = [0.001, 0.01, 0.05, 0.1, 0.5, 1, 5, 10, 20, 50]
    else:
        kgrid = [self.k]

    self.trainAndTune(trainingData, trainingLabels, validationData, validationLabels, kgrid)

  def trainAndTune(self, trainingData, trainingLabels, validationData, validationLabels, kgrid):
    """
    Trains the classifier by collecting counts over the training data, and
    stores the Laplace smoothed estimates so that they can be used to classify.
    Evaluate each value of k in kgrid to choose the smoothing parameter
    that gives the best accuracy on the held-out validationData.

    trainingData and validationData are lists of feature Counters.  The corresponding
    label lists contain the correct label for each datum.

    To get the list of all possible features or labels, use self.features and
    self.legalLabels.
    """
    # this part only calculates the prior and the likelihood, the posterior is calculated in the logJoint method
    self.likelihood = None
    self.prior = None

    # calculate prior
    self.prior = util.Counter()
    for label in trainingLabels:
        self.prior[label] += 1
    self.prior.normalize()

    # calculate likelihood estimator
    # keys will be each possible feature value for each feature
    feature_value_total = {}
    feature_value_counts = {}
    # make each and every feature its own separate naive bayes problem
    # use util.Counter to collect probability distributions
    # keys will be labels for sub-Counters
    for feature in self.features:
        feature_value_total[feature] = util.Counter() # captures label counts
        feature_value_counts[feature] = {0: util.Counter(), 1: util.Counter()} # captures feature counts given labels
        # the {0:1} dictionary is used to calculate feature probability of a given label in a specific features
        # this is because feature values are only {0:1} to indicate presence

    for i in xrange(len(trainingLabels)):
        label = trainingLabels[i]
        datum = trainingData[i]
        for feature, value in datum.iteritems():
            feature_value_total[feature][label] += 1 # gets total counts for each label
            feature_value_counts[feature][value][label] += 1  # gets total counts for each feature value given label


    arg_max_likelihood = {}
    top_acc = -1
    top_K = None
    # below is work for smoothing and choosing best k for best likelihood estimator
    for K in kgrid:
        likelihood = {}
        for feature in self.features:
            likelihood[feature] = {0: util.Counter(), 1: util.Counter()}

        # calculate the smoothed likelihood
        for feature in self.features:
            for label in self.legalLabels:
                likelihood[feature][0][label] = (feature_value_counts[feature][0][label] + K) / (feature_value_total[feature][label] + 2*K)
                likelihood[feature][1][label] = (feature_value_counts[feature][1][label] + K) / (feature_value_total[feature][label] + 2*K)

        self.likelihood = likelihood
        # find best smoothed likelihood by using classify method to check against validation data
        validation_answers = self.classify(validationData)
        count = 0
        for i in xrange(len(validation_answers)):
            if validation_answers[i] == validationLabels[i]:
                count += 1
        acc = count / len(validationLabels)

        if acc > top_acc:
            arg_max_likelihood = likelihood
            top_acc = acc
            top_K = K

    self.likelihood = arg_max_likelihood
    self.k = top_K

  def classify(self, testData):
    """
    Classify the data based on the posterior distribution over labels.

    You shouldn't modify this method.
    """
    guesses = []
    self.posteriors = [] # Log posteriors are stored for later data analysis (autograder).
    for datum in testData:
      posterior = self.calculateLogJointProbabilities(datum)
      guesses.append(posterior.argMax())
      self.posteriors.append(posterior)
    return guesses

  def calculateLogJointProbabilities(self, datum):
    """
    Returns the log-joint distribution over legal labels and the datum.
    Each log-probability should be stored in the log-joint counter, e.g.
    logJoint[3] = <Estimate of log( P(Label = 3, datum) )>

    To get the list of all possible features or labels, use self.features and
    self.legalLabels.
    """
    logJoint = util.Counter()
    "*** YOUR CODE HERE ***"
    for label in self.legalLabels:
        try:
            logJoint[label] = math.log(self.prior[label])
        except:
            logJoint[label] = 0
        for feature in self.likelihood:
            value = datum[feature]
            likelihood = self.likelihood[feature][value][label]
            try:
                logJoint[label] += math.log(likelihood)
            except:
                pass

    return logJoint

  def findHighOddsFeatures(self, label1, label2):
    """
    Returns the 100 best features for the odds ratio:
            P(feature=1 | label1)/P(feature=1 | label2)

    Note: you may find 'self.features' a useful way to loop through all possible features
    """
    featuresOdds = []

    "*** YOUR CODE HERE ***"
    util.raiseNotDefined()

    return featuresOdds
